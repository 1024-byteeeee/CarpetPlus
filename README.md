/carpet SuperWindCharge [boolean default=false] 风弹螺旋丸手里剑
/carpetVillageAlwaysBreed [boolean default=false] 村民一直繁殖（包括小村民）
/carpet SuperSponge [boolean default=false] 超级海绵
/carpet SuperSpongeRadius [int default=6] 超级海绵生效半径(需开启超级海绵)
/carpet playerSpecificChunks [boolean default=false] 启用玩家独立区块加载范围
/carpet playerChunkLoadRanges [String default=""] 玩家区块加载范围设置（请使用/playerchunk逐个设置）
/carpet defaultPlayerViewDistance [int default=10] 默认玩家区块加载范围（不用管）

关于玩家独立区块加载范围的指令：
1. 启用功能
/carpet playerSpecificChunks true

# 设置单个玩家
/playerchunk set 玩家A 10

# 设置多个玩家
/playerchunk set @a 10          # 所有玩家
/playerchunk set @r 8           # 随机玩家
/playerchunk set @p 12          # 最近的玩家

# 查看设置
/playerchunk get 玩家A
/playerchunk get @s             # 自己

# 重置设置
/playerchunk reset 玩家A
/playerchunk reset @a           # 重置所有玩家

# 列出所有设置
/playerchunk list

# 查看状态
/playerchunk status

# 统计信息
/chunkstats
