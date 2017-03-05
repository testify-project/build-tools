# Contributing

## Prerequisites
- [Install GitFlow](http://danielkummer.github.io/git-flow-cheatsheet)
- Initialize GitFlow:
```bash
$ git flow init
Branch name for production releases: master
Branch name for "next release" development: develop
Feature branch prefix: feature/
Bugfix branch prefix:
Release branch prefix: release/
Hotfix branch prefix: hotfix/
Support branch prefix: support/
Version tag prefix:
```

### Versioning
Testify has an automated release system and uses [Semantic Versioning][semver] version numbering system.

```
major.minor.patch
```

| number | meaning                                                                    |
| ------ | -------------------------------------------------------------------------- |
| major  | major version, with most probably incompatible change in API and behavior  |
| minor  | minor version, important enough change to bump this number                 |
| patch  | a released build number incremented automatically a pull request is merged |

### Adding a Feature
- Create a feature:
```bash
$ git flow feature start awesome-feature
```
- Do some development and commit to awesome-feature branch:
```bash
$ git commit -m "awesome-feature description" .
```
- Publish feature:
```bash
$ git flow feature publish awesome-feature
```
- Finish the feature:
```bash
$ git flow feature finish awesome-feature
```

### Performing Release
- Start release:
```bash
# replace x.x.x with release semantic version
$ git flow release start x.x.x
```
- Update the project version in pom files:
```bash
# replace x.x.x with release semantic version
$ mvn versions:set -DnewVersion=x.x.x
```
- Update CHANGELOG.md:
- Commit the changes:
```bash
# replace x.x.x with release semantic version
$ git commit -m "Updated version to x.x.x" .
```
- Finish the release:
```bash
# replace x.x.x with release semantic version
$ git flow release finish x.x.x # release semantic version
```
- Update next development project version in pom files:
```bash
# replace x.x.x with next development semantic version
$ mvn versions:set -DnewVersion=x.x.x-SNAPSHOT
```
- Commit the updated pom files:
```bash
# replace x.x.x with next development semantic version
$ git commit -m "Updated next development version to x.x.x-SNAPSHOT" .
```
- Push changes in develop and master branches and tags to remote repository:
```bash
$ git push origin develop master --tags
```

### Issue Pull Request
[Pull requests](http://help.github.com/send-pull-requests) are welcome.