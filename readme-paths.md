# Setting up Paths

## Windows

`<input needed, please insert helpful guide>`

## Unix

> Please note that the steps below will differ depending on which shell you use.

Find location of the `bin` directory in your downloaded (and extracted) `dragapult-app.zip` file. Good place to put this
in would be probably your home directory (`mkdir ~/.dragapult`).

Thereafter you need to export this path so the shell can find it.

Either once:

```bash
export PATH="$PATH:~/dragapult/bin"
```

Or on every log-on:

```bash
echo "export PATH=\"\$PATH:~/dragapult/bin\"" >> ~/.zshrc
source ~/.zshrc
```
