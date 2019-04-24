docker exec -i jersey-db mysql -uuser -ppassword register_db < drop-table.sql
docker exec -i jersey-db mysql -uuser -ppassword register_db < data-ddl.sql