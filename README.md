使用 cURL 测试

```
curl [option] [url]
```

常见参数：

    -X GET | POST | PUT | DELETE 自定义请求方法类型
    -H header 自定义请求头信息
    -d data 自定义请求需要携带的 cookie
    -F “file=@path/filename" 自定义需要上传的文件
    -v 显示服务器响应的详细信息