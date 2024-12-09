import os

# Ścieżka do katalogu z działającym kodem
base_dir = "C:/Users/Admin/Documents/GitHub/Java-JEE-Labs/src"

# Reguły zamiany
rename_rules = {
    "club": "club",
    "Club": "Club",
    "player": "player",
    "Player": "Player",
}

def rename_files_and_folders(root_path):
    for dirpath, dirnames, filenames in os.walk(root_path, topdown=False):
        # Zmieniamy nazwy plików
        for filename in filenames:
            new_filename = apply_rules(filename)
            os.rename(
                os.path.join(dirpath, filename),
                os.path.join(dirpath, new_filename)
            )
        
        # Zmieniamy nazwy folderów
        for dirname in dirnames:
            new_dirname = apply_rules(dirname)
            os.rename(
                os.path.join(dirpath, dirname),
                os.path.join(dirpath, new_dirname)
            )

def apply_rules(name):
    """Zastosuj reguły zamiany na nazwę."""
    for old, new in rename_rules.items():
        name = name.replace(old, new)
    return name

# Uruchamiamy proces zmiany nazw
rename_files_and_folders(base_dir)
