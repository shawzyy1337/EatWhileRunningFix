/**
 * Checks if the player is currently busy with an animation.
 * @return true if the player is busy, false otherwise
 */
public boolean isBusy() {
    if (!this.isUsingItem()) {
        return false;
    }

    Item item = this.getActiveItem().getItem();
    EnumAnimation animation = item.getUseAnimation(this.getActiveItem());
    
    return switch (animation) {
        case DRINK, EAT, BLOCK -> true;
        default -> false;
    };
}
