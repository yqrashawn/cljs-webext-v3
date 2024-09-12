
## Firefox (Developer Addition)
in `about:config` 

default `extensions.webextensions.default-content-security-policy.v3` is `script-src 'self'; upgrade-insecure-requests;`

change it to `script-src 'self' 'unsafe-eval' http://localhost:8063;`


`extensions.webextensions.base-content-security-policy.v3`
default is
`script-src 'self' 'wasm-unsafe-eval';`
change it to `script-src 'self' 'unsafe-eval' 'wasm-unsafe-eval' http://localhost:8063; script-src-elem 'self' http://localhost:8063;`
