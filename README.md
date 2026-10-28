# Basic Calculator (Android) with GitHub Actions CI

A minimal Kotlin Android app (add / subtract / multiply / divide) with one unit test and a
CI pipeline that runs **lint → unit tests → debug build** on every pull request.

```
.github/workflows/android-ci.yml   CI workflow (job name: "build")
scripts/protect-main.sh            Branch protection via GitHub CLI
app/                               Android app + CalculatorTest
```

## Step 1 – Push the app (no CI yet)
```bash
git init -b main
git add . ':!.github'
git commit -m "Add basic calculator app"
git remote add origin git@github.com:OWNER/REPO.git
git push -u origin main
```

## Step 2 – Add the workflow (Gradle caching included)
```bash
git checkout -b add-ci
git add .github
git commit -m "Add Android CI workflow"
git push -u origin add-ci        # open a PR; the "build" check runs
```
Merge it once green. The check named `build` must have run at least once before
GitHub lets you select it as a required check.

## Step 3 – Require the check before merging
Option A (script): `./scripts/protect-main.sh OWNER/REPO`

Option B (UI): **Settings → Branches → Add branch protection rule**
- Branch name pattern: `main`
- ✅ Require a pull request before merging
- ✅ Require status checks to pass before merging → add **build**
- ✅ Require branches to be up to date before merging
- ✅ Do not allow bypassing the above settings

> Private repos on GitHub Free don't support branch protection; use a public repo or a paid plan.

## Step 4 – Verify
**Green PR:** `git checkout -b clean-change`, edit a comment, push, open PR → green check, Merge enabled.

**Red PR:** `git checkout -b broken-change`, change the expected value in
`CalculatorTest.kt` from `5.0` to `6.0`, push, open PR → red ✗ on `build`,
and the Merge button is disabled ("Required statuses must pass").

## Local run
```bash
gradle lintDebug testDebugUnitTest assembleDebug
```
(Optional: run `gradle wrapper --gradle-version 8.7` once and commit the wrapper if you'd
rather use `./gradlew`; then drop `gradle-version` from the workflow and use `./gradlew`.)
