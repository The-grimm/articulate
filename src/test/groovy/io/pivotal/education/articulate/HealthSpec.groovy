package io.pivotal.education.articulate

import groovy.json.JsonSlurper
import okhttp3.OkHttpClient
import okhttp3.Request
import spock.lang.Specification

class HealthSpec extends Specification {
  def baseUrl = System.getenv().BASE_URL ?: "http://localhost:8080"

  def "should pass health check"() {
    given:
    def client = new OkHttpClient()
    def request = new Request.Builder()
        .header("Accept", "application/json")
        .url("${baseUrl}/health")
        .build()

    and:
    def parser = new JsonSlurper()

    when:
    def response = client.newCall(request).execute()
    def text = response.body().string()
    def json = parser.parseText(text)

    then:
    json.status == "UP"
  }

}
