# Custom Hive UDF in Scala

This project you can be used as a base to develop custom Hive UDF/functions using Scala.
The need of developping UDFs in Java/Scala arises to simplify SQL code and implement unit testing which often is a big deal.

## Overall workflow
1. Develop your UDFs with classes extending the UDF class
2. Unit testing
3. Generate a fat jar with sbt
4. Add the jar to Hive
5. Register the functions (mapping functions to Scala classes)
6. Using the registered SQL functions

## Run the project

## Generate the fat jar
First thing, we need to generate the jar so it will add as a volume to the container of the master node.

```bash
sbt assembly
```

### Run the whole big data environnement
Now we need to set up Hadoop and Hive, I've used the [bigdata-docker-compose](https://github.com/panovvv/bigdata-docker-compose) repo which basically contains a well done docker-compose.yml and few data to play with.

Let' run all the docker containers:
```bash
docker-compose up -d
```
Doing that should download all the docker images and run the containers.
To verify if the whole thing worked, you could go ahead and visit the YARN WebUI http://localhost:8088/cluster.
The docker-compose maps volumes to the master node :
- ./hive/data:/data - Data used that simulate a data ingestion
- ./hive/scripts/bash/setup-data.sh:/scripts/setup-data.sh - Script that add csv data into HDFS
- ./hive/scripts/sql/create_table.sql:/scripts/create_table.sql - SQL script that create the Hive table and load the data from HDFS
- ./target/scala-2.11/HiveUDFScala-assembly-0.1.jar:/jars/HiveUDFScala-assembly-0.1.jar - the fat jar containing the UDF classes

### Set up data in Hive
Let's get inside the master node
```bash
docker-compose exec master bash
```

And run the following command to ingest data in HDFS
```bash
bash scripts/setup-data.sh
```

We can quicky check if the data has been ingested properly by the following.
We should be able to see the grades.csv in HDFS.
```bash
hdfs dfs -ls /
```

We can now create the Hive table and load the data
```bash
hive -f create_table.sql
```
The following Hive query should let us see if the data was correctly loaded into Hive.
```bash
hive> SELECT * FROM grades;
```

### Map the Scala UDF class to a Hive SQL function
First need to add the jar to Hive
```bash
hive> ADD JAR /jars/HiveUDFScala-assembly-0.1.jar
```
Once it's done we only need to register the Hive SQL function.
```bash
CREATE FUNCTION area_from_ssn as 'com.yinshangyi.udfs.AreaFromSSN';
```
And that is it, you can now use the area_from_ssn from your Hive SQL code.
```sql
SELECT area_from_ssn(SSN)
FROM grades;
```