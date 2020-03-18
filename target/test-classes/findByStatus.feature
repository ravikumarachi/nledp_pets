Feature:

  @withService
  Scenario Outline: find pets by Status
    Given I triggered findByStatus endpoint with "<status>", "<name>"
    Then I should see response code as 200
    And I should see number of pets 1091

    Examples:
      | status    | name   |
      | available | doggie |

  @withOutService
  Scenario Outline: find pets by Status
    And I should see number of pets 509 in json response file "<jsonFile>"

    Examples:
      | jsonFile |
      | response |

  @withMockserver
  Scenario Outline: find pets by Status
    Given I triggered findByStatus endpoint with "<status>", "<name>" using mockServer
    Then I should see response code as 200
    And I should see number of pets 509

    Examples:
      | status    | name   |
      | available | doggie |