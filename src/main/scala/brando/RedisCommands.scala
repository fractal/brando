package brando

import scala.language.implicitConversions

import akka.util.ByteString

sealed trait RedisCommand {
  def name: String
  override def toString = name
}

sealed trait KeyCommand extends RedisCommand


/**
 * Delete a key
 * @param key [key ...] 
 */
case object Del extends KeyCommand {
  override val name = "DEL"
}
 
/**
 * Return a serialized version of the value stored at the specified key.
 * @param key 
 */
case object Dump extends KeyCommand {
  override val name = "DUMP"
}

/**
 * Determine if a key exists
 * @param key 
 */
case object Exists extends KeyCommand {
  override val name = "EXISTS"
}

/**
 * Set a key's time to live in seconds
 * @param key 
 * @param seconds
 */
case object Expire extends KeyCommand {
  override val name = "EXPIRE"
}

/**
 * Set the expiration for a key as a UNIX timestamp
 * @param key 
 * @param timestamp
 */
case object ExpireAt extends KeyCommand {
  override val name = "EXPIREAT"
}

/**
 * Find all keys matching the given pattern
 * @param pattern 
 */
case object Keys extends KeyCommand {
  override val name = "KEYS"
}

/**
 * Atomically transfer a key from a Redis instance to another one.
 * @param host 
 * @param port 
 * @param key 
 * @param destination-db 
 * @param timeout 
 */
case object Migrate extends KeyCommand {
  override val name = "MIGRATE"
}

/**
 * Move a key to another database
 * @param key 
 * @param db 
 */
case object Move extends KeyCommand {
  override val name = "MOVE"
}

/**
 * Inspect the internals of Redis objects
 * @param subcommand [arguments [arguments ...]] 
 */
case object Object extends KeyCommand {
  override val name = "OBJECT"
}

/**
 * Remove the expiration from a key
 * @param key
 */
case object Persist extends KeyCommand {
  override val name = "PERSIST"
}

/**
 * Set a key's time to live in milliseconds
 * @param key milliseconds
 */
case object PExpire extends KeyCommand {
  override val name = "PEXPIRE"
}

/**
 * Set the expiration for a key as a UNIX timestamp specified in milliseconds
 * @param key milliseconds-timestamp
 */
case object PExpireAt extends KeyCommand {
  override val name = "PEXPIREAT"
}
 
/**
 * Get the time to live for a key in milliseconds
 * @param key 
 */
case object PTtl extends KeyCommand {
  override val name = "PTTL"
}

/**
 * Return a random key from the keyspace
 * @param key 
 */
case object RandomKey extends KeyCommand {
  override val name = "RANDOMKEY"
}

/**
 * Rename a key
 * @param key 
 * @param newkey
 */
case object Rename extends KeyCommand {
  override val name = "RENAME"
}

/**
 * Rename a key, only if the new key does not exist
 * @param key 
 * @param newkey
 */
case object RenameNx extends KeyCommand {
  override val name = "RENAMENX"
}

/**
 * Create a key using the provided serialized value, previously obtained using DUMP.
 * @param key 
 * @param ttl
 * @param serialized-value
 */
case object Restore extends KeyCommand {
  override val name = "RESTORE"
}

/**
 * Sort the elements in a list, set or sorted set
 * @param  key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]] [ASC|DESC] [ALPHA] [STORE destination]
 */
case object Sort extends KeyCommand {
  override val name = "SORT"
}

/**
 * Get the time to live for a key
 * @param  key 
 */
case object Ttl extends KeyCommand {
  override val name = "TTL"
}
/**
 * Determine the type stored at key
 * @param  key 
 */
case object Type extends KeyCommand {
  override val name = "TYPE"
}

/**
 * String commands
 */
sealed trait StringCommand extends RedisCommand

/**
 * Append a value to a key
 * @param key 
 * @param value
 */
case object Append extends StringCommand {
  override val name = "APPEND"
}

/**
 * String commands
 */
sealed trait BitCommand extends RedisCommand

/**
 * Count set bits in a string
 * @param key [start] [end] 
 */
case object BitCount extends StringCommand with BitCommand {
  override val name = "BITCOUNT"
}

/**
 * Perform bitwise operations between string
 * @param operation destkey key [key ...]
 */
case object BitOp extends StringCommand with BitCommand {
  override val name = "BITOP"
}

/**
 * Increment/decrement commands
 */
sealed trait CounterCommand extends RedisCommand

/**
 * Decrement the integer value of a key by one
 * @param key 
 */
case object Decr extends CounterCommand with StringCommand{
  override val name = "DECR"
}

/**
 * Decrement the integer value of a key by the given number
 * @param key 
 * @param decrement
 */
case object DecrBy extends CounterCommand with StringCommand {
  override val name = "DECRBY"
}

/**
 * Read data
 */
sealed trait PullCommand extends RedisCommand

/**
 * Get the value of a key
 * <b>Note: For consistency with Set, GET is all Caps.</b>
 * @param key 
 */
case object GET extends PullCommand with StringCommand {
  override val name = "GET"
}

/**
 * Returns the bit value at offset in the string value stored at key
 * @param key offset 
 */
case object GetBit extends PullCommand with BitCommand with StringCommand {
  override val name = "GETBIT"
}

/**
 * Read data
 */
sealed trait BulkCommand extends RedisCommand

/**
 * Get a substring of the string stored at a key
 * @param key start end
 */
case object GetRange extends PullCommand with BulkCommand with StringCommand {
  override val name = "GETRANGE" 
}

/**
 * Write data
 */
sealed trait PushCommand extends RedisCommand

/**
 * Set the string value of a key and return its old value
 * @param key value
 */
case object GetSet extends PullCommand with PushCommand with StringCommand {
  override val name = "GETSET" 
}

/**
 * Increment the integer value of a key by one
 * @param key 
 */
case object Incr extends CounterCommand with StringCommand {
  override val name = "INCR" 
}

/**
 * Increment the integer value of a key by the given amount
 * @param key increment
 */
case object IncrBy extends CounterCommand with StringCommand {
  override val name = "INCRBY" 
}

/**
 * Increment the float value of a key by the given amount
 * @param key increment
 */
case object IncrByFloat extends CounterCommand with StringCommand {
  override val name = "INCRBYFLOAT" 
}

/**
 * Get the values of all the given keys
 * @param key start end
 */
case object MGet extends PullCommand with BulkCommand with StringCommand {
  override val name = "MGET" 
}

/**
 * Set multiple keys to multiple values
 * @param key value [key value ...]
 */
case object MSet extends PushCommand with BulkCommand with StringCommand {
  override val name = "MSET" 
}

/**
 * Set multiple keys to multiple values, only if none of the keys exist
 * @param  key value [key value ...]
 */
case object MSetNx extends PushCommand with BulkCommand with StringCommand {
  override val name = "MSETNX" 
}

/**
 * Expiration policy in milliseconds
 */
sealed trait ExpireCommand extends RedisCommand

/**
 * Set the value and expiration in milliseconds of a key
 * @param  key milliseconds value
 */
case object PSetEx extends PushCommand with ExpireCommand with StringCommand {
  override val name = "PSETEX" 
}

/**
 * 
 * Set the string value of a key. 
 * <b>Note: Set is already taken, so renaming to RSet.</b> 
 * @param key value [EX seconds] [PX milliseconds] [NX|XX]
 */
case object SET extends PushCommand with StringCommand {
  override val name = "SET"
}

/**
 * Sets or clears the bit at offset in the string value stored at key
 * @param key offset value
 */
case object SetBit extends PushCommand with BitCommand with StringCommand {
  override val name = "SETBIT" 
}

/**
 * Set the value and expiration of a key
 * @param  key seconds value
 */
case object SetEx extends PushCommand with ExpireCommand with StringCommand {
  override val name = "SETEX" 
}

/**
 * Set the value of a key, only if the key does not exist
 * @param key value
 */
case object SetNx extends PushCommand with ExpireCommand with StringCommand {
  override val name = "SETNX" 
}

/**
 * Overwrite part of a string at key starting at the specified offset
 * @param  key offset value
 */
case object SetRange extends PushCommand with BulkCommand with StringCommand {
  override val name = "SETRANGE" 
}

/**
 * Get the length of the value stored in a key
 * @param key 
 */
case object StrLen extends StringCommand {
  override val name = "STRLEN" 
}

/**
 * Sets commands
 */
sealed trait HashCommand extends RedisCommand

/**
 * Delete one or more hash fields
 * @param  key field [field ...]
 */
case object HDel extends BulkCommand with HashCommand {
  override val name = "HDEL" 
}

/**
 * Determine if a hash field exists
 * @param  key field
 */
case object HExists extends HashCommand {
  override val name = "HEXISTS" 
}

/**
 * Get the value of a hash field
 * @param  key field
 */
case object HGet extends PullCommand with HashCommand {
  override val name = "HGET" 
}

/**
 * Get all the fields and values in a hash
 * @param  key 
 */
case object HGetAll extends PullCommand with BulkCommand with HashCommand {
  override val name = "HGETALL" 
}

/**
 * Increment the integer value of a hash field by the given number
 * @param  key field increment 
 */
case object HIncrBy extends CounterCommand  with HashCommand {
  override val name = "HINCRBY" 
}

/**
 * Increment the float value of a hash field by the given amount
 * @param  key field increment 
 */
case object HIncrByFloat extends CounterCommand with HashCommand {
  override val name = "HINCRBYFLOAT" 
} 

/**
 * Get all the fields in a hash
 * @param  key
 */
case object HKeys extends BulkCommand with HashCommand {
  override val name = "HKEYS" 
} 

/**
 * Get the number of fields in a hash
 * @param  key
 */
case object HLen extends HashCommand {
  override val name = "HLEN" 
} 

/**
 * Get the values of all the given hash fields
 * @param  key field [field ...]
 */
case object HMGet extends BulkCommand with PullCommand with HashCommand {
  override val name = "HMGET" 
}

/**
 * Set multiple hash fields to multiple values
 * @param  key field value [field value ...]
 */
case object HMSet extends BulkCommand with PushCommand with HashCommand {
  override val name = "HMSET" 
}

/**
 * Set the string value of a hash field
 * @param  key field value
 */
case object HSet extends PushCommand with HashCommand {
  override val name = "HSET" 
}
 
/**
 * Set the value of a hash field, only if the field does not exist
 * @param  key field value
 */
case object HSetNx extends PushCommand with HashCommand {
  override val name = "HSETNX" 
}

/**
 * Get all the values in a hash
 * @param key
 */
case object HVals extends PullCommand with HashCommand {
  override val name = "HVALS" 
}

 
/**
 * Sets commands
 */
sealed trait SetCommand extends RedisCommand

/**
 * Add one or more members to a set
 * @param key member [member ...] 
 */
case object SAdd extends BulkCommand with SetCommand {
  override val name = "SADD" 
}

/**
 * Get the number of members in a set
 * @param key  
 */
case object SCard extends SetCommand {
  override val name = "SCARD" 
}

/**
 * Subtract multiple sets
 * @param key [key ...]  
 */
case object SDiff extends SetCommand {
  override val name = "SDIFF" 
}

/**
 * Sets commands
 */
sealed trait PersistentCommand extends RedisCommand

/**
 * Subtract multiple sets and store the resulting set in a key
 * @param destination key [key ...]  
 */
case object SDiffStore extends PersistentCommand with SetCommand{
  override val name = "SDIFFSTORE" 
}

/**
 * Intersect multiple sets
 * @param key [key ...]
 */
case object SInter extends SetCommand {
  override val name = "SINTER" 
}

/**
 * Intersect multiple sets and store the resulting set in a key
 * @param destination key [key ...]
 */
case object SInterStore extends PersistentCommand with SetCommand {
  override val name = "SINTERSTORE" 
}

/**
 * Determine if a given value is a member of a set
 * @param  key member
 */
case object SIsMember extends SetCommand {
  override val name = "SISMEMBER" 
}

/**
 * Get all the members in a set
 * @param  key 
 */
case object SMembers extends SetCommand {
  override val name = "SMEMBERS" 
}

/**
 * Move a member from one set to another
 * @param source destination member 
 */
case object SMove extends SetCommand {
  override val name = "SMOVE" 
}
 
/**
 * Remove and return a random member from a set
 * @param key
 */
case object SPop extends SetCommand {
  override val name = "SPOP" 
}
 
/**
 * Get one or multiple random members from a set
 * @param key [count]
 */
case object SRandMember  extends BulkCommand with  SetCommand {
  override val name = "SRANDMEMBER" 
}

/**
 * Remove one or more members from a set
 * @param key member [member ...]
 */
case object SRem extends BulkCommand with  SetCommand {
  override val name = "SREM" 
}

/**
 * Add multiple sets
 * @param key [key ...]
 */
case object SUnion extends BulkCommand with  SetCommand {
  override val name = "SUNION" 
}

/**
 * Add multiple sets and store the resulting set in a key
 * @param  destination key [key ...]
 */
case object SUnionStore extends BulkCommand with PersistentCommand with SetCommand {
  override val name = "SUNIONSTORE" 
}


/**
 * Connection commands
 */
sealed trait ConnectionCommand extends RedisCommand

/**
 * Authenticate to the server
 */
case object Auth extends ConnectionCommand {
  override val name = "AUTH"
}

/**
 * Echo the given string
 */
case object Echo extends ConnectionCommand {
  override val name = "ECHO"
}

/**
 * Ping the server
 */
case object Ping extends ConnectionCommand {
  override val name = "PING"
}

/**
 * Close the connection
 */
case object Quit extends ConnectionCommand {
  override val name = "QUIT"
}

/**
 * Change the selected database for the current connection
 */
case object Select extends ConnectionCommand {
  override val name = "SELECT" 
}


/**
 * Server commands
 */
sealed trait ServerCommand extends RedisCommand

/**
 * Asynchronously rewrite the append-only file
 */
case object BgRewriteAof extends ServerCommand {
  override val name = "BGREWRITEAOF"
}

/**
 * Asynchronously save the dataset to disk
 */
case object BgSave extends ServerCommand {
  override val name = "BGSAVE"
}

sealed trait ClientCommand extends ServerCommand 

/**
 * Kill the connection of a client
 * @param ip:port
 */
case object ClientKill extends ClientCommand {
  override val name = "CLIENT KILL"
}

/**
 * Get the list of client connections
 */
case object ClientList extends ClientCommand {
  override val name = "CLIENT LIST"
}

/**
 * Get the current connection name
 */
case object ClientGetName extends ClientCommand {
  override val name = "CLIENT GETNAME"
}

/**
 * Set the current connection name
 */
case object ClientSetName extends ClientCommand {
  override val name = "CLIENT SETNAME"
}

sealed trait ConfigCommand extends ServerCommand 

/**
 * Get the value of a configuration parameter
 * @param parameter
 */
case object ConfigGet extends ConfigCommand {
  override val name = "CONFIG GET"
}

/**
 * Set a configuration parameter to the given value
 * @param parameter 
 * @param value
 */
case object ConfigSet extends ConfigCommand {
  override val name = "CONFIG SET"
}

/**
 * Reset the stats returned by INFO
 */
case object ConfigResetStat extends ConfigCommand {
  override val name = "CONFIG RESETSTAT"
}

/**
 * Return the number of keys in the selected database 
 */
case object DbSize extends ServerCommand {
  override val name = "DBSIZE"
}

/**
 * Debugging commands
 */
sealed trait DebugCommand extends ServerCommand

/**
 * Get debugging information about a key
 * @param key
 */
case object DebugObject extends DebugCommand {
  override val name = "DEBUG OBJECT"
}

/**
 * Make the server crash
 */
case object DebugSegFault extends DebugCommand {
  override val name = "DEBUG SEGFAULT"
}

/**
 * Remove keys from database commands
 */
sealed trait FlushCommand extends ServerCommand

/**
 * Remove all keys from all databases
 */
case object FlushAll extends FlushCommand {
  override val name = "FLUSHALL"
}

/**
 * Remove all keys from current database
 */
case object FlushDb extends FlushCommand {
  override val name = "FLUSHDB"
}

/**
 * Get information and statistics about the server
 * @param [section]
 */
case object Info extends ServerCommand {
  override val name = "INFO"
}

/**
 * Get the UNIX time stamp of the last successful save to disk
 */
case object LastSave extends ServerCommand {
  override val name = "LASTSAVE"
}

/**
 * Listen for all requests received by the server in real time
 */
case object Monitor extends ServerCommand {
	override val name = "MONITOR"
}

/**
 * Synchronously save the dataset to disk
 */
case object Save extends ServerCommand {
   override val name = "SAVE"
}

/**
 * Synchronously save the dataset to disk and then shut down the server
 * @param [NOSAVE] [SAVE]
 */
case object Shutdown extends ServerCommand {
   override val name = "SHUTDOWN"
}

/**
 * Make the server a slave of another instance, or promote it as master
 * @param host
 * @param port
 */
case object SlaveOf extends ServerCommand {
   override val name = "SLAVEOF"
}

/**
 * Manages the Redis slow queries log
 * @param subcommand [argument]
 */
case object SlowLog extends ServerCommand {
   override val name = "SLOWLOG"
}

/**
 * Internal command used for replication
 */
case object Sync extends ServerCommand {
   override val name = "SYNC"
}

/**
 * Return the current server time
 */
case object Time extends ServerCommand {
   override val name = "TIME"
}

object Commands {
	implicit def commandToByteString[T <: RedisCommand](command : T) = 
	  ByteString(command.name)  
}