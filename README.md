# 블로그 v2 코드 연습

### 1. DB 및 사용자 생성
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
USE greendb;
```

### 2. 프로젝트 세팅
- application.yml
- view 생성