# Test joins with Ignite 3

## Run Ignite 3 instance

### Run beta1

At the time of writing, you can download the beta 1, 
as explained [here](https://ignite.apache.org/docs/3.0.0-beta/installation/installing-using-zip).

``` shell script
${IGNITE_HOME}/bin/ignite3db start
```

### Run dockerized snapshot

You can run the container of the snapshot version,
as explained [here](https://ignite.apache.org/docs/3.0.0-beta/installation/installing-using-docker)

``` shell script
docker run -it --rm -p 10300:10300 -p 10800:10800 apacheignite/ignite3
```

## Init the cluster

You can use the console to init the cluster.
At the time of writing, the beta 1 console can activate the snapshot version too.
Other commands are not compatible.

``` shell script
ignite3-cli-3.0.0-beta1> ./ignite3
```

* Press Y (or enter) to connect

``` shell script
cluster init -n=sampleCluster -m=defaultNode
```

## Run the project

### Using snapshot

``` shell script
mvn clean install
```

### Using beta1

``` shell script
mvn clean install -P
```



