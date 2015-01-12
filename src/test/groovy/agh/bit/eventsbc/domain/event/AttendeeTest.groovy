package agh.bit.eventsbc.domain.event

import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId
import spock.lang.Specification

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
class AttendeeTest extends Specification {

    def sampleId = new AttendeeId(123L)
    def validEmail = "silver.surfer@gmail.com"
    def sampleFirstName = "Silver"
    def sampleSecondName = "Surfer"

    def "Should not allow to create attendee without first name"() {
        when:
        new Attendee(sampleId, validEmail, null, sampleSecondName)

        then:
        thrown(NullPointerException.class)
    }

    def "Should not allow to create attendee without second name"() {
        when:
        new Attendee(sampleId, validEmail, sampleFirstName, null)

        then:
        thrown(NullPointerException.class)
    }

    def "Should not allow to create attendee without correct email"() {
        given:
        def invalidEmail = "bazin@ga"

        when:
        new Attendee(sampleId, invalidEmail, sampleFirstName, sampleSecondName)

        then:
        thrown(IllegalArgumentException.class)
    }
}
