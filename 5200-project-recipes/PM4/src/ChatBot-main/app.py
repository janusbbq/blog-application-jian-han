from flask import Flask, render_template, request, jsonify
import openai
import os
from dotenv import load_dotenv


app = Flask(__name__)

openai.api_key = "sk-EXAMPLE"


persona_prompt = """

"""


# 初始对话历史，包括个人简介
conversation_history = [{"role": "system", "content": persona_prompt}]

@app.route("/")
def index():
    return render_template('chat.html')

@app.route("/get", methods=["GET", "POST"])
def chat():
    user_message = request.form["msg"]
    model_response = get_Chat_response(user_message)
    return model_response

def get_Chat_response(user_message):
    # 添加用户消息到对话历史
    conversation_history.append({"role": "user", "content": user_message})

    # 向OpenAI模型发送请求，包括对话历史
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=conversation_history
    )

    # 提取模型的回复
    model_reply = response.choices[0].message["content"]

    # 将模型的回复添加到对话历史
    conversation_history.append({"role": "assistant", "content": model_reply})

    return model_reply

if __name__ == '__main__':
    app.run()