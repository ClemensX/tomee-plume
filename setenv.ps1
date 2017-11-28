#		properties.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver");
#		properties.put(PersistenceUnitProperties.JDBC_URL, Util.buildJdbcUrl(credentials));
#		properties.put(PersistenceUnitProperties.JDBC_USER, credentials.username);
#		properties.put(PersistenceUnitProperties.JDBC_PASSWORD, credentials.password);

# set env vars for local db:

$env:rbs_jdbc_driver="org.hsqldb.jdbcDriver"
$env:rbs_jdbc_url="jdbc:hsqldb:mem:client1"
$env:rbs_jdbc_user="sa"
$env:rbs_jdbc_password=""

# local postgresql:
# org.postgresql.Driver
# jdbc:postgresql://localhost:5432/pcsregservice
# myuser
# mypassword

$env:rbs_jdbc_driver="org.postgresql.Driver"
$env:rbs_jdbc_url="jdbc:postgresql://localhost:5432/pcsregservice"
$env:rbs_jdbc_user="myuser"
$env:rbs_jdbc_password="mypassword"

$env:rbs_jdbc_driver
$env:rbs_jdbc_url
$env:rbs_jdbc_user
$env:rbs_jdbc_password
