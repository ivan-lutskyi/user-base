# user-base Makefile
# Copyright © 2025 Ivan Lutskyi
# Email: postscriptum.no@gmail.com

JAVA_SRC = src
BUILD_DIR = build/classes
MAIN_CLASS = com.userbase.UserBase

.PHONY: clean compile run

compile:
	@mkdir -p $(BUILD_DIR)
	@echo "Compiling Java sources..."
	@javac -d $(BUILD_DIR) -sourcepath $(JAVA_SRC) $(JAVA_SRC)/com/userbase/UserBase.java
	@echo "Compilation complete."

run: compile
	@echo "Running user-base..."
	@java -cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	@echo "Cleaning build files..."
	@rm -rf $(BUILD_DIR)
	@echo "Clean complete."
