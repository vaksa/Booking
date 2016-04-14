Feature: MavenBooking

  Scenario Outline: Seaching for hotels with correct dates
    Given Go to "http://www.booking.com"
    When Fill <value> in Destination/hotel name field
    And Select <checkin_month> in "checkin_year_month" dropdown
    And Select <checkin_day> in "checkin_monthday" dropdown
    And Select <checkout_month> in "checkout_year_month" dropdown
    And Select <checkout_day> in "checkout_monthday" dropdown
    And Click "Search" button
    Then Verify that all hotels on a page of results are located in <address>

    Examples: 
      | value           | checkin_month | checkin_day | checkout_month | checkout_day | address       |
      | "New York City" | "May 2016"    | "Sun 1"     | "May 2016"     | "Thu 5"      | "New York"    |
      | "Las Vegas"     | "June 2016"   | "Wed 1"     | "June 2016"    | "Mon 30"     | "Las Vegas"   |
      | "Los Angeles"   | "June 2016"   | "Wed 1"     | "June 2016"    | "Wed 1"      | "Los Angeles" |
