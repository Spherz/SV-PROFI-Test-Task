FROM gradle:jdk17

WORKDIR /postaldelivery

COPY . /postaldelivery

CMD ["gradle", "bootRun"]