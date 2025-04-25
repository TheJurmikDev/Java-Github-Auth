---

# 🔐 Java GitHub HWID Authenticator

A secure Java-based authentication system that validates users based on their HWID using data fetched from a private GitHub repository. This system is designed to protect your software by allowing access only to authorized users.

## 📦 Features

- 🔒 HWID-based authentication
- 🔐 AES encryption & decryption
- 🌐 GitHub API request and parsing
- ❌ Secure program termination if authentication fails
- ⚙️ Silent console suppression during exit

## 🚀 How It Works

1. Sends an HTTP request to a GitHub file containing encrypted user HWIDs.
2. Decodes and decrypts the GitHub content.
3. Compares it with the local machine's HWID.
4. Runs the program only if the HWID is valid.

## 📁 Project Structure

```
dev.thejurmik
├── Main.java                       # Main authentication logic
├── InfoProvider.java               # Constants for encrypted token and address
├── parser/
│   └── ParseGit.java               # Decodes and extracts Base64 GitHub content
├── utils/
│   ├── getter/AuthInfo.java        # Retrieves address and token (encrypted)
│   ├── hwid/HWID.java              # Generates local machine HWID
│   ├── requests/
│   │   ├── GithubRequest.java      # Sends GitHub request
│   │   └── HttpResponseStatus.java # Gets HTTP status code
│   └── system/SecureExit.java      # Securely halts the app and silences output
└── cryptography/
    ├── Encryptor.java              # AES encryption utility
    └── Decryptor.java              # AES decryption utility
```

## 🔧 Setup

> ⚠️ **Warning:** This project uses encryption and GitHub for authentication. You must use the provided `Encryptor` to encrypt the `ADRESS` and `KEY` values in `InfoProvider`.

1. Clone this repository.
2. Encrypt your GitHub file URL and token using `Encryptor.encrypt()`.
3. Replace `InfoProvider.java` values with the encrypted versions.
4. Build and run the project.

## 💻 Example Output

If authenticated successfully:

```
Successfully authenticated
```

Otherwise, the app will terminate silently.

## 🛡️ Security Notes

- Ensure your GitHub repo is private.
- Never store unencrypted credentials.
- The app halts securely and silences all output upon failure.

---