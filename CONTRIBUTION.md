# How to contribute

## Issues

Please describe the issue:

* the plugin version
* the OS and IDE version. Open your IDE and go to <kbd>Help</kbd>, <kbd>About</kbd>, then <kbd>Copy</kbd>
* the stacktrace, if any in the event log (in the bottom-right corner of your IDE)
* any useful information

## Pull requests

* ensure unit tests are green by running `./gradlew test verifyPlugin`
* please reformat new and modified code only: do not reformat the whole project or entire existing file (in other words,
  try to limit the amount of changes in order to speed up code review)
* please write comments in english only
* don't hesitate to add your name or nickname (and LinkedIn profile, etc.) to the contributors list ;-)
* please email me (jonathan.lermitage@gmail) or send a private message on Twitter (JLermitage), **I have some free licenses for you**

Tip: to support new icons, you will want to
edit [this file](https://github.com/vesavlad/intellij-extra-icons-plugin/blob/master/src/main/java/lermitage/intellij/extra/icons/ExtraIconProvider.java#L24)
. All icons and selection rules are registered here. Icons are stored in `src/main/resources/extra-icons/`.  
Try the plugin in a standalone IDE with `./gradlew runIde`.

## Feature requests

If you are not comfortable with Java development, you can ask me to support new icons. Please:

* provide SVG or 32x32 PNG icon(s)
* explain the conditions to apply the icon(s), like filename patterns

You can open an issue, or email me (jonathan.lermitage@gmail.com).

:warning: Feature requests with no icon or no explanations will be ignored.
