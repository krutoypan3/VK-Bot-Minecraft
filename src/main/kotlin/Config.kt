object Config {
    object MinecraftConnect {
        const val SERVER_IP = "127.0.0.1"
        const val SERVER_RCON_PORT = 25575
        const val SERVER_RCON_PASSWORD = "SECRET_VALUE"
    }
    object VkConnect {
        const val GROUP_ID = SECRET_VALUE           // ID Группы с ботом

        /**
         * Токен группы с ботом
         *
         * Как получить:
         * 1) Зайти на страницу сообщества (вы - администратор, короче должны быть права на него)
         * 2) Управление -> Работа с API -> Создать ключ, этот ключ вставляете в значение переменной
         * 3) Во вкладке Long Poll API поставить значение "Long Poll API": 'Включено'
         */
        const val ACCESS_TOKEN = "SECRET_VALUE"     // Токен группы с ботом (управление -> работа с API -> создать ключ; во вкладке )
        const val PRIMARY_CHAT_PEER_ID = 2000000001 // ID основной беседы бота
    }

    /**
     * Отсылать онлайн статус в ВК бота при изменении
     */
    const val IS_LIVE_STATUS = true
}