import os
import sys
import subprocess
from dotenv import load_dotenv

# Load variables from .env
load_dotenv()

def run_liquibase(command, extra_args=None):
    # Construct the base command
    cmd = [
        "liquibase",
        f"--url={os.getenv('DB_URL')}",
        f"--username={os.getenv('DB_USERNAME')}",
        f"--password={os.getenv('DB_PASSWORD')}",
        f"--search-path={os.getenv('LIQUIBASE_SEARCH_PATH')}",
        f"--changelog-file={os.getenv('CHANGELOG_FILE')}",
        command
    ]

    if extra_args:
        cmd.extend(extra_args)

    try:
        print(f"Executing: {' '.join(cmd[:-1])} [PASSWORD HIDDEN]")
        subprocess.run(cmd, check=True)
    except subprocess.CalledProcessError as e:
        print(f"\nError: Liquibase failed with exit code {e.returncode}")
        sys.exit(1)
    except FileNotFoundError:
        print("\nError: 'liquibase' command not found. Is it in your PATH?")
        sys.exit(1)

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python migrate.py [update | rollback] [count]")
        sys.exit(1)

    action = sys.argv[1]

    if action == "update":
        run_liquibase("update")
    elif action == "rollback":
        count = sys.argv[2] if len(sys.argv) > 2 else "1"
        run_liquibase("rollbackCount", [count])
    else:
        print(f"Unknown action: {action}")