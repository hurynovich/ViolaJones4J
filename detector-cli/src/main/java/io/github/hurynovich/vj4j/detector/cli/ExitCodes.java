package io.github.hurynovich.vj4j.detector.cli;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExitCodes {
    public static final int CASCADE_FILE_NOT_PROVIDED = 1;
    public static final int CASCADE_FILE_NOT_EXISTS = 2;
    public static final int CASCADE_FILE_NOT_FILE = 3;
}
