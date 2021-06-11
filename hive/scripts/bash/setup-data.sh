#!/bin/bash
echo "Send the csv file to HDFS"
hdfs dfs -put /../data/grades.csv /
