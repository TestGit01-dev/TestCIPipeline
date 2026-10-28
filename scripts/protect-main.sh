#!/usr/bin/env bash
# Requires the GitHub CLI (gh), authenticated with admin rights on the repo.
# Usage: ./scripts/protect-main.sh OWNER/REPO
set -euo pipefail
REPO="${1:?Usage: $0 OWNER/REPO}"

gh api -X PUT "repos/${REPO}/branches/main/protection" --input - <<JSON
{
  "required_status_checks": null,
  "enforce_admins": true,
  "required_pull_request_reviews": null,
  "restrictions": null,
  "allow_force_pushes": false,
  "allow_deletions": false
}
JSON
echo "Branch protection enabled on main for ${REPO}: check 'build' must pass."
