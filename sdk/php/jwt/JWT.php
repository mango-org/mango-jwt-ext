<?php

namespace php\jwt;

use php\time\Time;

abstract class JWT
{
    /**
     * @param string $key
     * @param string $subject
     * @return string
     */
    public static function generateToken(string $key, string $subject): string {
        return "token :^)";
    }

    /**
     * @param string $key
     * @param string $token
     * @return string
     */
    public static function extractSubject(string $key, string $token): string {
        return "subject!";
    }

    /**
     * @param string $key
     * @param string $token
     * @return Time
     */
    public static function extractExpiration(string $key, string $token): Time {
        return new Time();
    }

    /**
     * @param string $key
     * @param string $token
     * @return bool
     */
    public static function isTokenExpired(string $key, string $token): bool {
        return false;
    }
}
