package vm;

public enum Interrupt {
    NONE, INVALID_ADDRESS, INVALID_INSTRUCTION, STOP, OVERFLOW, TRAP, INVALID_SYSTEM_CALL, noInterruptIO,
    noInterrupt
}