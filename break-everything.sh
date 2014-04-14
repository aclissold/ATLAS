#!/bin/bash

for i in {1..300}
do
    curl "localhost:9000/comment/delete/$i"
done

for i in {1..300}
do
    curl --data "page=Saturn&user=Monty+Python&text=spamspamspamspamspam" \
        localhost:9000/comment/save
done
