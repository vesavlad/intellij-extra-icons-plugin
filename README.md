[![Stand With Ukraine](https://raw.githubusercontent.com/vshymanskyy/StandWithUkraine/main/banner2-direct.svg)](https://vshymanskyy.github.io/StandWithUkraine/)

<h1 align="center">
    <a href="https://plugins.jetbrains.com/plugin/11058-extra-icons">
      <img src="./src/main/resources/META-INF/pluginIcon.svg" width="84" height="84" alt="logo"/>
    </a><br/>
    Simple Icons
</h1>

<p align="center">
    <a href="https://plugins.jetbrains.com/plugin/11058-extra-icons"><img src="https://img.shields.io/jetbrains/plugin/v/11058-extra-icons.svg"/></a>
    <a href="https://plugins.jetbrains.com/plugin/11058-extra-icons"><img src="https://img.shields.io/jetbrains/plugin/d/11058-extra-icons.svg"/></a>
    <a href="https://github.com/vesavlad/intellij-extra-icons-plugin/blob/master/LICENSE.txt"><img src="https://img.shields.io/github/license/vesavlad/intellij-extra-icons-plugin.svg"/></a>
    <a href="https://github.com/vesavlad/intellij-extra-icons-plugin/graphs/contributors"><img src="https://img.shields.io/github/contributors/vesavlad/intellij-extra-icons-plugin"/></a><br>
    <a href="https://github.com/vesavlad/intellij-extra-icons-plugin/actions?query=workflow%3A%22Build%22"><img src="https://github.com/vesavlad/intellij-extra-icons-plugin/workflows/Build/badge.svg"/></a>
    <a href="https://github.com/vesavlad/intellij-extra-icons-plugin/actions?query=workflow%3A%22Compatibility%22"><img src="https://github.com/vesavlad/intellij-extra-icons-plugin/workflows/Compatibility/badge.svg"/></a><br>
    <a href="https://github.com/vesavlad/intellij-extra-icons-plugin/actions?query=workflow%3A%22Build+EAP%22"><img src="https://github.com/vesavlad/intellij-extra-icons-plugin/workflows/Build%20EAP/badge.svg"/></a>
    <a href="https://github.com/vesavlad/intellij-extra-icons-plugin/actions?query=workflow%3A%22Compatibility+EAP%22"><img src="https://github.com/vesavlad/intellij-extra-icons-plugin/workflows/Compatibility%20EAP/badge.svg"/></a>
</p>

Intellij IDEA (Community and Ultimate) plugin that adds icons for files like Travis YML, Appveyor YML, Git sub-modules, etc.  
You can also register your own icons in order to override file icons, but also all [IDE icons](https://jetbrains.design/intellij/resources/icons_list/) (including toolbars, menus, etc.). It works with all JetBrains products like IntelliJ (ultimate and community), PyCharm, WebStorm, DataGrip, etc.  
To get started with this plugin, please see this [guide](docs/GET_STARTED.md).

1. [Download](#download)
2. [Build](#build)  
3. [Contribution](#how-to-contribute)  
4. [Known issues](#known-issues)  
5. [License](#license)  
6. [Credits](#contributors)  
7. [Screenshots](#screenshots)  

## Download

Download plugin from the [JetBrains marketplace](https://plugins.jetbrains.com/plugin/11058-extra-icons) or via your IDE: <kbd>File</kbd>, <kbd>Settings...</kbd>, <kbd>Plugins</kbd>, <kbd>Marketplace</kbd>.

## Build

Install JDK11+. You should be able to start Gradle Wrapper (`gradlew`). See Gradle commands below.  
You may also want to see the [development FAQ](./docs/DEV_FAQ.md) if you faced an issue.

### Gradle commands

```bash
$ ./gradlew buildPlugin        # build plugin to build/distributions/*.zip
$ ./gradlew runIde             # try plugin in a standalone IDE
$ ./gradlew dependencyUpdates  # check for dependencies updates
$ ./gradlew verifyPlugin       # validate plugin.xml descriptors as well as plugin's archive structure
$ ./gradlew runPluginVerifier  # check for compatibility issues with IDE
$ ./gradlew test               # run tests
```

Additionally, take a look at the `Makefile` script: it contains useful commands to build, run and test the plugin, check for dependencies updates and some maintenance tasks. Show available commands by running `make help`. 

### Optimizations

Optionally, you may want to install SVGO in order to optimize SVG icons. Install SVGO with `npm install -g svgo`, then optimize SVG files by running `make svgo`.

## How to contribute

Please see [CONTRIBUTION.md](CONTRIBUTION.md).

## Known issues

Please see [KNOWN_ISSUES.md](KNOWN_ISSUES.md) and [GitHub open issues](https://github.com/vesavlad/intellij-extra-icons-plugin/issues).

## License

MIT License. In other words, you can do what you want: this project is entirely OpenSource, Free and Gratis.  
You only have to pay a subscription if you want to support my work by using the version that is published to the JetBrains marketplace. If you don't want to (or can't) support my work, you can still use old releases (up to 1.69), or package and install your own release for free. If you have any question, please see the [license FAQ](docs/LICENSE_FAQ.md).

## Contributors

* (author) Jonathan Lermitage (<jonathan.lermitage@gmail.com>)
* Edoardo Luppi (<lp.edoardo@gmail.com>)
* Matthias Kunnen ([github.com/MatthiasKunnen](https://github.com/MatthiasKunnen))
* Florian Böhm ([github.com/Sheigutn](https://github.com/Sheigutn))
* Mateusz Bajorek (<mateusz.bajorek@gmail.com>)
* Bouteiller Alan ([github.com/bouteillerAlan](https://github.com/bouteillerAlan))
* Gaëtan Maisse ([github.com/gaetanmaisse](https://github.com/gaetanmaisse))
* please see the [contributors list](https://github.com/vesavlad/intellij-extra-icons-plugin/graphs/contributors)

## Screenshots

![Dark Screenshot](docs/screenshots/intellijidea-ce_dark.png)

![Screenshot](docs/screenshots/intellijidea-ce.png)

![Config Panel Screenshot](docs/screenshots/config-panel.png)
