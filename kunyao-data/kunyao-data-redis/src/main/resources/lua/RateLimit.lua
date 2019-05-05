local key = 'ratelimit:'..KEYS[1]
local limit = tonumber(ARGV[1])
local expireTime=ARGV[2]
local times= redis.call('incr',key)
if times == 1 then
    redis.call('expire', key , expireTime)
end
if times > limit then
    return 0
end
return 1