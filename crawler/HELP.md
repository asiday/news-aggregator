# Getting Started

## How to build the image using the Dockerfile.

Here is used a multi-stage build in Dockerfile with two build stages:
* First stage builds the maven jar file.
* Second stage creates an image.
   
Here is the build command from the parent project `news-aggregator`:

```
$ docker build 
    -t asiday/news-crawler:0.0.1 .
    -f crawler/Dockerfile  
    --build-arg key=XXX
```

Explanations:
* `-t` : is a tage to give the name for the image.
[DockerHub username]/[Repository name in DockerHub]:[Version]
* `.`  : is a build context. Here is current directory.
* `-f` : is the name and path to the Dockerfile
* `--build-arg` : write the api-key.
