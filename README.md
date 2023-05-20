## Readme for kubernetes install & run

To run the services on a existing kubernetes cluster, type in the following commands:

```bash
kubectl apply -f microservices.yaml
```

This will create the services and deployments for the microservices. To check if everything is running, type in:

```bash
kubectl get pods
```

This will show you all the pods running on the cluster. There should be 4 pods.
To access the services, use postman or curl to send a request to the services. The services are available on the apache server on port 8001. The services are available on the following endpoints:

- localhost:8001/category/...
- localhost:8001/product/...

The data is stored in a mysql database as a stateful set.

Before viewing the services in istio, you need to label the namespace with istio-injection=enabled. To do this, type in the following command:

```bash
kubectl label namespace default istio-injection=enabled
```

Then re-apply the microservices.yaml file:

```bash
kubectl delete -f microservices.yaml
kubectl apply -f microservices.yaml
```

To view the istio dashboard, type in the following command:

```bash
istioctl dashboard kiali
```

To view the grafana dashboard, type in the following command:

```bash
istioctl dashboard grafana
```
