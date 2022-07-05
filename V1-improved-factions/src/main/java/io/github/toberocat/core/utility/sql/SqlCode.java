package io.github.toberocat.core.utility.sql;

import io.github.toberocat.core.utility.callbacks.TryRunnable;
import io.github.toberocat.core.utility.language.Parseable;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;

public class SqlCode {

    public static final String CREATE_LAYOUT = readFileSql("sql/create_table_layout.sql");

    public static final String CREATE_FACTION = readFileSql("sql/create/create_faction.sql");
    public static final String CREATE_DESCRIPTION = readFileSql("sql/create/create_descriptions.sql");

    public static final String UPDATE_FACTION = readFileSql("sql/update/update_faction.sql");

    public static TryRunnable<PreparedStatement> execute(@NotNull final MySql sql, @NotNull String code, final Parseable... placeholders) {
        // Replace all placeholders
        if (placeholders != null) {
            for (Parseable placeholder : placeholders)
                code = code.replaceAll(placeholder.getParse(), placeholder.getTo());
        }

        return sql.evalTry(code);
    }

    public static String readFileSql(@NotNull String resPath) {
        InputStream is = SqlCode.class.getClassLoader().getResourceAsStream(resPath);
        if (is == null)
            throw new IllegalArgumentException("Couldn't resolve the local file path to the sql file");

        StringBuilder textBuilder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader(is,
                Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textBuilder.toString();
    }
}