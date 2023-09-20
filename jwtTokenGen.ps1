# Generate a random byte array as a secret
$secretBytes = [byte[]]::new(32)  # Adjust the length as needed
[Security.Cryptography.RNGCryptoServiceProvider]::Create().GetBytes($secretBytes)

# Encode the secret in base64 format
$base64Secret = [Convert]::ToBase64String($secretBytes)

Write-Output "Base64 Secret: $base64Secret"
