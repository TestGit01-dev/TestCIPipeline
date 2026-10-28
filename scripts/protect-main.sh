#!/usr/bin/env bash
# Requires the GitHub CLI (gh), authenticated with admin rights on the repo.
# Usage: ./scripts/protect-main.sh OWNER/REPO
set -euo pipefail
REPO="${1:?Usage: $0 OWNER/REPO}"

gh api -X PUT "repos/${REPO}/branches/develop/protection" --input - <<JSON
{
  "required_status_checks": { "strict": true, "contexts": ["build", "Lint", "Unit tests"] },
  "enforce_admins": true,
  "required_pull_request_reviews": null,
  "restrictions": null,
  "allow_force_pushes": false,
  "allow_deletions": false
}
JSON
echo "Branch protection enabled on develop for ${REPO}: check 'build' must pass."
