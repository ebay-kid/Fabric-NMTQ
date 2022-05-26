# Fabric-NMTQ
NMTQ stands for No Moved Too Quickly

Basically removes the checks for entities and vehicles and prevents them from being lagged back for high-speed movement.

I highly doubt any other mod needs ServerPlayNetworkHandler (where code is messed with) so this should be compatible with everything. Open an issue otherwise.
### You don't want this mod if you're worried about hackers.

### Technical details:
A mixin into ServerPlayNetworkHandler redirects the 2 calls to Vec3D.lengthSquared() to a method which returns 1 billion. Since this value is then subtracted from some other variable, it becomes negative right before a greater than check (so it's almost always false)

## Config:
`#` is a commented line

Key-value pairs are separated by `=`

Current options (all `true`/`false`), the default config is false:

```
playerMoveTooQuick
vehicleMoveTooQuick
playerMoveWrong
vehicleMoveWrong
```

MIT Licensed code
