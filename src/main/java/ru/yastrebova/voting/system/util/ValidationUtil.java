package ru.yastrebova.voting.system.util;

import ru.yastrebova.voting.system.model.BaseEntity;

public class ValidationUtil {
    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }
}
