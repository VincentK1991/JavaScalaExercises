#!/bin/bash
echo "Enter the package name: "
read package_name
mkdir -p {src/main/java/$package_name,test/{java/$package_name,resources}}
