package io.github.hurynovich.vj4j.detector.cli;

import picocli.CommandLine;

final class VersionProvider implements CommandLine.IVersionProvider {

    @Override
    public String[] getVersion() throws Exception {
        return new String[]{"Version: 1.0.0-SNAPSHOT"};
    }
}
