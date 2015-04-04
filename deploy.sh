#!/usr/bin/env bash
# author: Abhishek Agrawal

export TOMCAT=$HOME/apache-tomcat-7.0.59
export PROJ=$HOME/DBProject/university-housing

# To check if project directory exist
if ! [ -e $PROJ ] ; then
    echo "[ERROR]: Given Project directory doesn't exist...."
    echo "[INFO] ------------------------------------------------------------------------------------"
    exit 1
fi

# To check if tomcat directory exist
if ! [ -e $TOMCAT ] ; then
    echo "[ERROR]: Tomcat directory doesn't exist...."
    echo "[INFO] ------------------------------------------------------------------------------------"
    exit 1
fi

cd $PROJ
echo "[INFO] -------------------------------"
echo "[INFO] -------------------------------"
echo "[INFO] Cleaning target directory "
echo "[INFO] -------------------------------"
rm -rf target/*

echo "[INFO] Building app "
echo "[INFO] -------------------------------"
mvn package

echo "[INFO] -------------------------------"
echo "[INFO] Checking Tomcat Server Status "
echo "[INFO] -------------------------------"

process=`ps -ef | grep $TOMCAT | grep -v grep | awk '{print $2}'`
   if [ ! -z "$process" ]; then
       echo "[INFO] TOMCAT INSTANCE ALREADY RUNNING ...."  
       cd $TOMCAT/bin/
       ./shutdown.sh
       echo "[INFO] TOMCAT OLD INSTANCE STOPPED .... "
       echo "[INFO] -------------------------------"
       sleep 6
fi

cd $TOMCAT
rm -rf logs/*
rm -rf webapps/uhousing*

cd $PROJ
mv target/*.war $TOMCAT/webapps/

echo "[INFO] -------------------------------"
echo "[INFO] TOMCAT NEW INSTANCE STARTED .... "
echo "[INFO] -------------------------------"
cd $TOMCAT/bin
./startup.sh
sleep 5

cd $TOMCAT/webapps
mv uhousing-1.0 uhousing
cp $PROJ/lib/* $TOMCAT/webapps/uhousing/WEB-INF/lib/

echo "[INFO] -------------------------------"
echo "[INFO] APP DEPLOYED SUCCESSFULLY .... "
echo "[INFO] -------------------------------"
