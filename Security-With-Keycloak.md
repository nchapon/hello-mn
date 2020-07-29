Security with keycloak
======================

SecureResource (Cf
[<file:./src/main/java/hello/mn/security/SecureResource.java>](~/Projects/nchapon/hello-mn/src/main/java/hello/mn/security/SecureResource.java))
is secured with JWT RBAC.

This resource provided two endpoints accessible only for authenticated
users or services with roles.

Keycloak Set Up
---------------

Keycloak runs with Docker on <http://localhost:8082> you can use
admin/admin to access to the console.

You can run keycloak with

``` {.shell}
cd infrastructure
docker-compose up -d
```

On startup this command will import `keycloak/realm-export.json` this
will create a realm **micronaut** with one client configured as service
account :

-   client-id : hello-mn
-   client-secret : 43246458-4d3d-4e6a-a15a-1063cb3c4452

Get Access Token
----------------

Keycloak token endpoints are secured with Basic Authentication from
client-id and client-secret encoded in base64 and propagated in HTTP
Header.

``` {#basic-auth .shell}
auth=$(echo -n "hello-mn:43246458-4d3d-4e6a-a15a-1063cb3c4452" | base64)
echo $auth
```

``` {.example}
aGVsbG8tbW46NDMyNDY0NTgtNGQzZC00ZTZhLWExNWEtMTA2M2NiM2M0NDUy
```

To get a valid `access_token` you should execute this request where
`$auth` is the authorization value created above.

``` {#access-token .shell var="auth=basic-auth" results="output"}
curl -X POST http://localhost:8082/auth/realms/micronaut/protocol/openid-connect/token \
-H "Authorization: Basic $auth" \
-H "Content-Type: application/x-www-form-urlencoded" \
-d grant_type=client_credentials \
| jq --raw-output '.access_token'
```

``` {.example}
eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJla3RhZ3RhaXRtNVZDNHdiQWhPSlNidXl6Zk9xRmR3TmIySm1nclkxRHVRIn0.eyJleHAiOjE1OTYwNjkyNjUsImlhdCI6MTU5NjAzMzI2NSwianRpIjoiYmEyOTk4NGItMTY2Ny00YjdlLTgzYTUtOGRiMDAxNzE2NjJjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2F1dGgvcmVhbG1zL21pY3JvbmF1dCIsInN1YiI6IjM3ZTA3NDUxLTQ3OTAtNGUwNC1iZTdmLTdkN2M1ZGQ1ZTVmNSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImhlbGxvLW1uIiwic2Vzc2lvbl9zdGF0ZSI6ImIxODlkYTgxLTczMjgtNGVlYS1hMDhhLTdhZjdkN2EyMmZhZCIsImFjciI6IjEiLCJzY29wZSI6ImhlbGxvLW1uLXJlYWQgaGVsbG8tbW4td3JpdGUiLCJjbGllbnRIb3N0IjoiMTcyLjIzLjAuMSIsImNsaWVudElkIjoiaGVsbG8tbW4iLCJyb2xlcyI6WyJtbi11c2VyIiwibW4tYWRtaW4iXSwiY2xpZW50QWRkcmVzcyI6IjE3Mi4yMy4wLjEifQ.FuyrSGeVD5jGbOLfq_p5EYlm20FkR6E7RhoCCxSbGu3UCjcDBrzT3jkQ3oHRsLUlBAMz5ybJtoifye9YTUVtNfBb8O_a0MFU-UCbKI-C5oXRWuW4Xz4Wh0tn0GsHtxmG__-Zf1P6mirYYR2j6idvrvjjvMBak_x_wSvbm6bP19Rrl3mn_3bwyS-gwyRxgxEDkT4cvEguimxMhEOBTWePR6oe0BIe36NLXS25Yx1aVJTBUf0KxpItM3duabhEKnBd0-Ablv8p8P16dtV80mNayvPnJPgGEWRNk0axa0vcXFVZjxE7O-qxzsHQGxJswlhKmDRkHE_H-zqqX_ZtCtf-mw
```

JWT Payload
-----------

The payload JWT token returned has two roles :

-   mn-user
-   mn-admin

``` {.example}
{
  "exp": 1596069274,
  "iat": 1596033274,
  "jti": "b526d6a0-4564-45f1-8355-c274795a07fe",
  "iss": "http://localhost:8082/auth/realms/micronaut",
  "sub": "37e07451-4790-4e04-be7f-7d7c5dd5e5f5",
  "typ": "Bearer",
  "azp": "hello-mn",
  "session_state": "6c271fd3-c8b4-4955-98c9-1a675c7aa4a8",
  "acr": "1",
  "scope": "hello-mn-read hello-mn-write",
  "clientHost": "172.23.0.1",
  "clientId": "hello-mn",
  "roles": [
    "mn-user",
    "mn-admin"
  ],
  "clientAddress": "172.23.0.1"
}
```

JWT signature can be validated with Keycloak public key that can be
accessible here :
<http://localhost:8082/auth/realms/micronaut/protocol/openid-connect/certs>

This URL is defined in `src/main/resources/application.yaml` for
validating JWT token.

Invoke SecureResource
---------------------

### /secure

Replace `$token` with `access_token` value

``` {.shell var="token=access-token" results="value code"}
curl -H "Authorization: Bearer $token" http://localhost:8080/secure
```

### /secure/admin

Replace `$token` with `access_token` value

``` {.shell var="token=access-token" results="value code"}
curl -H "Authorization: Bearer $token" http://localhost:8080/secure/admin
```

Returns all authenticated information :

``` {.shell}
{
  "name": "37e07451-4790-4e04-be7f-7d7c5dd5e5f5",
  "attributes": {
    "sub": "37e07451-4790-4e04-be7f-7d7c5dd5e5f5",
    "clientHost": "172.23.0.1",
    "clientId": "hello-mn",
    "roles": [
      "mn-user",
      "mn-admin"
    ],
    "iss": "http://localhost:8082/auth/realms/micronaut",
    "typ": "Bearer",
    "clientAddress": "172.23.0.1",
    "acr": "1",
    "azp": "hello-mn",
    "scope": "hello-mn-read hello-mn-write",
    "exp": 1596071344000,
    "session_state": "5d5f0c5d-b695-49b3-a399-ae1c83e155c2",
    "iat": 1596035344000,
    "jti": "9f981b4e-7b29-4f3e-a6b8-23928cdac2ef",
    "username": "37e07451-4790-4e04-be7f-7d7c5dd5e5f5"
  }
}
```
