package com.infoshareacademy.object;

import org.junit.jupiter.api.Test;

class AuthorTest {
    @Test
    void getSetName() {
        //GIVEN
        Author author = new Author();
        author.setName("Franek");
        String result;

        //WHEN
        result = author.getName();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Franek");

    }
}
