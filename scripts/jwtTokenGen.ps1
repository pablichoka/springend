# Generate a random byte array as a secret
$secretBytes = [byte[]]::new(32)  # Adjust the length as needed
[Security.Cryptography.RNGCryptoServiceProvider]::Create().GetBytes($secretBytes)

# Encode the secret in base64 format
$base64Secret = [Convert]::ToBase64String($secretBytes)

Write-Output "Base64 Secret: $base64Secret"

$choice = Read-Host "Do you to establish it as an env user variable? (y/n)"

if ($choice -eq "y") {
    [Environment]::SetEnvironmentVariable("JWT_TOKEN", $base64Secret, [EnvironmentVariableTarget]::USER)    
} elseif ($choice -eq "n") {
    exit
} else {
    Write-Output "Invalid answer. Please select y(es) or n(o)"
}









