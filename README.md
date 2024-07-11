# drools-poc

## References
1) https://paras301.medium.com/drools-drive-your-rules-from-database-24be9a0ae231
2) https://medium.com/@bm28/use-drools-as-rule-engine-in-application-6114e590c02f
3) https://paras301.medium.com/drools-agenda-group-vs-activation-group-and-how-to-use-them-together-eccd64ff3a7e

## Fetching rules from database
Samples JSON rules (must be added in mongo DB)
```
{
    "name":"BM-RULE-002",
    "priority": 2,
    "activationGroup":"TEST_ACT_GROUP",
    "agendaGroup":"MAIN",
    "dateStart":"21-Jan-1992",
    "dateEnd":"21-Jan-2092",
    "status":"ACTIVE",
    "expression":"virtualCardNumber > 2",
    "eligible": false
}
{
    "name":"BM-RULE-001",
    "priority": 1,
    "activationGroup":"TEST_ACT_GROUP",
    "agendaGroup":"MAIN",
    "dateStart":"21-Jan-1991",
    "dateEnd":"21-Jan-2091",
    "status":"ACTIVE",
    "expression":"(physicalCardNumber >= 1 || virtualCardNumber > 2)",
    "eligible": true
}
