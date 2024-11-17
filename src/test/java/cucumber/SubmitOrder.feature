@tag
Feature: End to end test for adding products,check out and submitting the order
	@Test
  Scenario Outline: Positive test of submitting the order
    Given User is on the login Page
    When User used valid username<Username> and password<Password>
    And adds items to cart and submits the order
    Then "Thankyou for the order." is displayed on the screen

    Examples: 
      | Username          | Password    |
      | fit.dsf@gmail.com | Qwerty1!    |
      | anshika@gmail.com | Iamking@000 |
