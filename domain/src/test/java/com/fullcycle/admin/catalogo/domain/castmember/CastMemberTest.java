package com.fullcycle.admin.catalogo.domain.castmember;

import com.fullcycle.admin.catalogo.domain.castmemeber.CastMember;
import com.fullcycle.admin.catalogo.domain.castmemeber.CastMemberType;
import com.fullcycle.admin.catalogo.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CastMemberTest {

    @Test
    public void givenAValidParams_whenCallsNewMember_thenInstantiateAMember() {
        //Given
        final var expectedName = "Vin Diesel";
        final var expectedType = CastMemberType.ACTOR;

        //When
        final var actualMember =
                CastMember.newMember(expectedName, expectedType);

        //Then
        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());
        Assertions.assertEquals(expectedName, actualMember.getName());
        Assertions.assertEquals(expectedType, actualMember.getType());
        Assertions.assertNotNull(actualMember.getCreatedAt());
        Assertions.assertNotNull(actualMember.getUpdatedAt());
    }

    @Test
    public void givenInvalidNullName_whenCallsNewCastMember_shouldReceiveANotification() {
        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            CastMember.newMember(expectedName, expectedType);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallsNewCastMember_shouldReceiveANotification() {
        final String expectedName = " ";
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            CastMember.newMember(expectedName, expectedType);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidNameWithLengthGreaterThan255_whenCallNewCastMemberAndValidate_shouldReceiveANotification() {
        final String expectedName = """
                Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
                composição das posturas dos órgãos dirigentes com relação às suas atribuições.
                Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
                manutenção das novas proposições.
                """;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            CastMember.newMember(expectedName, expectedType);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidNullType_whenCallsNewCastMember_shouldReceiveANotification() {
        final String expectedName = "Vin Diesel";
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            CastMember.newMember(expectedName, expectedType);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdate_shouldReceiveCastMemberUpdated() {

        final var expectedName = "Vin Diesel";
        final var expectedType = CastMemberType.ACTOR;

        final var actualCastMember = CastMember.newMember("Braian", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualCastMember);
        Assertions.assertNotNull(actualCastMember.getId());

        final var actualId = actualCastMember.getId();
        final var actualCreatedAt = actualCastMember.getCreatedAt();
        final var actualUpdatedAt = actualCastMember.getUpdatedAt();

        actualCastMember.update(expectedName, expectedType);

        Assertions.assertNotNull(actualCastMember);
        Assertions.assertEquals(actualId, actualCastMember.getId());
        Assertions.assertEquals(expectedName, actualCastMember.getName());
        Assertions.assertEquals(expectedType, actualCastMember.getType());
        Assertions.assertEquals(actualCreatedAt, actualCastMember.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualCastMember.getUpdatedAt()));
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithNullName_shouldReceiveANotification() {

        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualCastMember = CastMember.newMember("Braian", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualCastMember);
        Assertions.assertNotNull(actualCastMember.getId());

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            actualCastMember.update(expectedName, expectedType);
        });

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithEmptyName_shouldReceiveANotification() {

        final var expectedName = " ";
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualCastMember = CastMember.newMember("Braian", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualCastMember);
        Assertions.assertNotNull(actualCastMember.getId());

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            actualCastMember.update(expectedName, expectedType);
        });

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithNullType_shouldReceiveANotification() {

        final var expectedName = "Vin Diesel";
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        final var actualCastMember = CastMember.newMember("Braian", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualCastMember);
        Assertions.assertNotNull(actualCastMember.getId());

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            actualCastMember.update(expectedName, expectedType);
        });

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithLengthGreaterThan255_shouldReceiveANotification() {

            final String expectedName = """
                Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
                composição das posturas dos órgãos dirigentes com relação às suas atribuições.
                Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
                manutenção das novas proposições.
                """;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualCastMember = CastMember.newMember("Braian", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualCastMember);
        Assertions.assertNotNull(actualCastMember.getId());

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            actualCastMember.update(expectedName, expectedType);
        });

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}