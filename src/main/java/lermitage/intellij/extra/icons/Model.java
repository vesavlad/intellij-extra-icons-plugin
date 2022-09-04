// SPDX-License-Identifier: MIT

package lermitage.intellij.extra.icons;

import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.XCollection;
import lermitage.intellij.extra.icons.cfg.SettingsService;
import lermitage.intellij.extra.icons.enablers.IconEnablerType;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Model {

    @OptionTag
    private String id;
    @OptionTag
    private String ideIcon;
    @OptionTag
    private String icon;
    @OptionTag
    private String description;
    @OptionTag
    private ModelType modelType;
    @OptionTag
    private IconType iconType;
    /** Indicates if a <b>user</b> model is enabled. To know if a built-in model
     * is enabled, see {@link SettingsService#getDisabledModelIds()}. */
    @OptionTag
    private boolean enabled = true;
    @XCollection
    private List<ModelCondition> conditions = new ArrayList<>(Collections.singletonList(new ModelCondition()));

    /** Alternative icons. Extra Icons will automatically generate alt icons (and ids, names, etc.) based on on this list. */
    private String[] altIcons;

    /** Tags associated to this model. Used to easily (de)activate models linked to specific tags. */
    private List<ModelTag> tags;

    /** For a model representing an alternative icon, the ID of the base model, otherwise null. */
    private Object parentId = null;

    // For XML deserializer (IntelliJ internals)
    @SuppressWarnings("unused")
    private Model() {
    }

    /**
     * Create a rule (Model) to apply given icon to a file. Once created, you need to
     * apply condition(s) to rule, like {@link #start(String...)}, {@link #eq(String...)}, etc.
     * @param id a unique id.
     * @param icon the path of the icon to apply, located in plugin resources folder.
     * @param description human readable description.
     */
    @NotNull
    @Contract("_, _, _ -> new")
    public static Model ofFile(String id, String icon, String description) {
        return createFileOrFolderModel(id, icon, description, ModelType.FILE, IconType.PATH);
    }

    /**
     * Create a rule (Model) to apply given icon to a folder. Once created, you need to
     * apply condition(s) to rule, like {@link #start(String...)}, {@link #eq(String...)}, etc.
     * @param id a unique id.
     * @param icon the path of the icon to apply, located in plugin resources folder.
     * @param description human readable description.
     */
    @NotNull
    @Contract("_, _, _ -> new")
    public static Model ofDir(String id, String icon, String description) {
        return createFileOrFolderModel(id, icon, description, ModelType.DIR, IconType.PATH);
    }

    /**
     * Create a rule (Model) to override given icon (ideIcon) with new icon (icon). Once created,
     * you must NOT apply conditions (like {@link #start(String...)}, {@link #eq(String...)}, etc.) to this rule,
     * they will be ignored.
     * @param id a unique id.
     * @param ideIcon the name of the icon to override. You can find icon
     *                names <a href="https://jetbrains.design/intellij/resources/icons_list/">here</a>: pick
     *                an icon and open the ZIP file; you can use the non-dark SVG file name.
     * @param icon the path of the icon to apply, located in plugin resources folder.
     * @param description human readable description.
     */
    @NotNull
    @Contract("_, _, _, _ -> new")
    public static Model ofIcon(String id, String ideIcon, String icon, String description) {
        return createIdeIconModel(id, ideIcon, icon, description, ModelType.ICON, IconType.PATH);
    }

    @NotNull
    @Contract("_, _, _, _, _ -> new")
    public static Model createFileOrFolderModel(String id, String icon, String description,
                                                ModelType modelType, IconType iconType) {
        Model model = new Model();
        model.id = id;
        model.icon = icon;
        model.description = description;
        model.modelType = modelType;
        model.iconType = iconType;
        return model;
    }

    @NotNull
    @Contract("_, _, _, _, _, _ -> new")
    public static Model createFileOrFolderModel(String id, String icon, String description,
                                                ModelType modelType, IconType iconType,
                                                List<ModelCondition> conditions) {
        Model model = createFileOrFolderModel(id, icon, description, modelType, iconType);
        model.conditions = conditions;
        return model;
    }

    @NotNull
    @Contract("_, _, _, _, _, _ -> new")
    public static Model createIdeIconModel(String id, String ideIcon, String icon, String description,
                                           ModelType modelType, IconType iconType) {
        Model model = new Model();
        model.id = id;
        model.ideIcon = ideIcon;
        model.icon = icon;
        model.description = description;
        model.modelType = modelType;
        model.iconType = iconType;
        return model;
    }

    @NotNull
    @Contract("_, _, _, _, _ -> new")
    public static Model createAltModel(Model baseModel, String altId, String altIdeIcon,
                                       String altIcon, String altDescription) {
        Model altModel = new Model();
        altModel.id = altId;
        altModel.ideIcon = altIdeIcon;
        altModel.icon = altIcon;
        altModel.description = altDescription;
        altModel.modelType = baseModel.modelType;
        altModel.iconType = baseModel.iconType;
        altModel.enabled = baseModel.enabled;
        altModel.conditions = baseModel.conditions;
        altModel.tags = baseModel.tags;
        altModel.parentId = baseModel.id;
        return altModel;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getIdeIcon() {
        return ideIcon;
    }

    public String getIcon() {
        return icon;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Nullable
    public Object getParentId() {
        return parentId;
    }

    /**
     * Condition: has given parent directory(s).
     * @param parents one or multiple possible directories, lowercase.
     */
    public Model parents(@NotNull String... parents) {
        getCurrentCondition().setParents(parents);
        return this;
    }

    /**
     * Condition: file/folder name starts with given string(s).
     * @param start strings, lowercase.
     */
    public Model start(@NotNull String... start) {
        getCurrentCondition().setStart(start);
        return this;
    }

    /**
     * Condition: file/folder name is equal to given string(s).
     * @param name strings, lowercase.
     */
    public Model eq(@NotNull String... name) {
        getCurrentCondition().setEq(name);
        return this;
    }

    /**
     * Condition: file/folder name may end with given string(s). This condition is optional.
     * Example: to catch README and README.md files, you will use {@code model.eq("readme").mayEnd(".md")}
     * @param end strings, lowercase.
     */
    public Model mayEnd(@NotNull String... end) {
        getCurrentCondition().setMayEnd(end);
        return this;
    }

    /**
     * Condition: file/folder name ends with given string(s).
     * @param end strings, lowercase.
     */
    public Model end(@NotNull String... end) {
        getCurrentCondition().setEnd(end);
        return this;
    }

    /**
     * Condition: file/folder name must not contain a dot.
     */
    public Model noDot() {
        getCurrentCondition().setNoDot();
        return this;
    }

    /**
     * Condition: file/folder <b>absolute path</b> satisfies given regular expression.
     * @param regex regular expression.
     */
    public Model regex(@NotNull @Language("RegExp") String regex) {
        getCurrentCondition().setRegex(regex);
        return this;
    }

    /**
     * Condition: project has given facet, like 'andoid', 'kotlin', 'python', 'spring' etc. You
     * can see and add facets in Project Structure / Project Settings / Facets.
     * @param facets facet(s), lowercase.
     */
    public Model facets(@NotNull String... facets) {
        getCurrentCondition().setFacets(Arrays.stream(facets).map(String::toLowerCase).toArray(String[]::new));
        return this;
    }

    /**
     * Condition: use an {@link lermitage.intellij.extra.icons.enablers.IconEnablerType},
     * like {@link lermitage.intellij.extra.icons.enablers.GitSubmoduleFolderEnabler}.
     * @param type IconEnablerType.
     */
    public Model iconEnabler(@NotNull IconEnablerType type) {
        getCurrentCondition().setIconEnablerType(type);
        return this;
    }

    /**
     * Register alternate icons.
     * @param altIcons an array of icons.
     */
    public Model altIcons(@NotNull String... altIcons) {
        this.altIcons = altIcons;
        return this;
    }

    /**
     * Associate this model to one or multiple tags. User will be able to enable and disable multiple models by tag.
     * @param tags an array of tags.
     */
    public Model tags(@NotNull ModelTag... tags) {
        this.tags = Arrays.stream(tags).collect(Collectors.toList());
        return this;
    }

    /**
     * Add a possible condition to current model: file/folder will have to satisfy one of configured conditions.
     */
    public Model or() {
        this.conditions.add(new ModelCondition());
        return this;
    }

    private ModelCondition getCurrentCondition() {
        return conditions.get(conditions.size() - 1);
    }

    public boolean check(String parentName, String fileName, @Nullable String fullPath, Set<String> facets, Project project) {
        for (ModelCondition condition : conditions) {
            if (condition.check(parentName, fileName, fullPath, facets, project)) {
                return true;
            }
        }
        return false;
    }

    public IconType getIconType() {
        return iconType;
    }

    public List<ModelCondition> getConditions() {
        return conditions;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getAltIcons() {
        return altIcons;
    }

    public List<ModelTag> getTags() {
        return tags == null ? Collections.emptyList() : tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return enabled == model.enabled &&
            Objects.equals(id, model.id) &&
            Objects.equals(ideIcon, model.ideIcon) &&
            Objects.equals(icon, model.icon) &&
            description.equals(model.description) &&
            modelType == model.modelType &&
            iconType == model.iconType &&
            conditions.equals(model.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ideIcon, icon, description, modelType, iconType, enabled, conditions);
    }
}
