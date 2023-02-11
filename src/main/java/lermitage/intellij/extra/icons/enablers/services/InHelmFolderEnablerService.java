// SPDX-License-Identifier: MIT

package lermitage.intellij.extra.icons.enablers.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import lermitage.intellij.extra.icons.enablers.AbstractInFolderEnabler;
import lermitage.intellij.extra.icons.enablers.IconEnabler;
import org.jetbrains.annotations.NotNull;

@Service
public final class InHelmFolderEnablerService extends AbstractInFolderEnabler implements IconEnabler {

    public InHelmFolderEnablerService(@NotNull Project project) {
        init(project);
    }

    public static InHelmFolderEnablerService getInstance(@NotNull Project project) {
        return project.getService(InHelmFolderEnablerService.class);
    }

    @NotNull
    @Override
    protected String[] getFilenamesToSearch() {
        return new String[]{"Chart.yaml", "values.yaml"};
    }

    @Override
    public String getName() {
        return "Helm icons";
    }
}
