## Time Endless Web App




## Testing

- postman
- curl

```
curl -F file=@"data.txt" http://localhost:8080/api/upload/
curl -F extraField="abc" -F files=@"data1.txt" -F files=@"data2.txt"  http://localhost:8080/api/upload/multi/
curl -F extraField="abc" -F files=@"data1.txt" -F files=@"data2.txt"  http://localhost:8080/api/upload/multi/model
```
