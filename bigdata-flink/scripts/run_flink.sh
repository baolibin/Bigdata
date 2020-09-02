#!/usr/bin/env bash

cluster="cluster_name"
queue="cluster_queue"
jar="xxx.jar"
class="xxx"

flink \
    --cluster "${cluster}" \
    -Dsecurity.kerberos.login.principal=xxx \
    -Dsecurity.kerberos.login.keytab=xxx \
    -Dstate.backend=filesystem \
    -Djob.name=xxx \
    -Dstate.checkpoints.dir=xxx/.flink/checkpoint/xxx \
    run \
    --jobmanager yarn-cluster \
    --allowNonRestoredState \
    --fromSavepoint xxx/.flink/checkpoint/xxx \
    --parallelism 1 \
    --yarnname "FLINK1.9_xxx_Streaming_Job" \
    --yarnqueue ${queue} \
    --yarntaskManagerMemory 2G \
    --yarnjobManagerMemory 2G \
    --class ${class} \
    "${jar}"
