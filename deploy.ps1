mvn clean install
docker build -f Dockerfile -t minhthongnguyen/coindesk:homework .
docker push minhthongnguyen/coindesk:homework