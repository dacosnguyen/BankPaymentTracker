# Bank transfer file format
A bank transfer has a format:

{Currency} {amount with optional thousands separators and optional two-digit fraction}

for example:
```text
USD 1000
CZK 100
USD -100
EUR 2,000.70
CZK 200

```

There must be a newline character at the end of the file.
