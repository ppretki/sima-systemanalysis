# sima-systemanalysis

```shell
docker swarm init --advertise-addr 192.168.1.28 && docker stack deploy --compose-file docker-compose.yml system_analysis
```


```shell
docker stack rm system_analysis
```

```shell
docker swarm leave --force
```