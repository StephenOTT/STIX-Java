
#Hashes

```java
    @JsonProperty("hashes") @JsonInclude(NON_EMPTY)
    @Size(min = 1, message = "Must have at least 1 hash value")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();
```